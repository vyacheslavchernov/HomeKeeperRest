package HomeKeeperRest.Controllers.Calculator;

import HomeKeeperRest.ApiCore.ApiResponse;
import HomeKeeperRest.ApiCore.Exceptions.IncorrectData;
import HomeKeeperRest.ApiCore.Payloads.ResponsePayload;
import HomeKeeperRest.ApiCore.ResponseUtil;
import HomeKeeperRest.ApiCore.Status;
import HomeKeeperRest.ApiCore.StatusCode;
import HomeKeeperRest.Controllers.Calculator.ResponseBody.Calculation;
import HomeKeeperRest.Controllers.Calculator.ResponseBody.Communal;
import HomeKeeperRest.Controllers.MonthData.MonthDataController;
import HomeKeeperRest.Domain.MonthData.AdditionalData;
import HomeKeeperRest.Domain.MonthData.CountersData;
import HomeKeeperRest.Domain.MonthData.MonthData;
import HomeKeeperRest.Domain.MonthData.TariffsData;
import HomeKeeperRest.Repo.MonthData.AdditionalDataRepo;
import HomeKeeperRest.Repo.MonthData.CountersDataRepo;
import HomeKeeperRest.Repo.MonthData.MonthDataRepo;
import HomeKeeperRest.Repo.MonthData.TariffsDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    private final String CONTROLLER_ENDPOINT = "api/calculator/";

    @Autowired
    private MonthDataController MONTH_DATA_CONTROLLER;
    @Autowired
    private MonthDataRepo monthDataRepo;
    @Autowired
    private CountersDataRepo countersDataRepo;
    @Autowired
    private TariffsDataRepo tariffsDataRepo;
    @Autowired
    private AdditionalDataRepo additionalDataRepo;

    @GetMapping(CONTROLLER_ENDPOINT + "calc")
    public ApiResponse getCalculation(@RequestParam(required = false) Long id) {
        if (id != null) {
            ResponsePayload tempPayload = MONTH_DATA_CONTROLLER.getMonthData(id, null, null).getPayload();
            MonthData monthData = tempPayload instanceof MonthData ? (MonthData) tempPayload : null;
            MonthData prevMonthData;

            if (monthData == null) {
                return ResponseUtil.buildError(
                        new IncorrectData("Не найдено месяца с id " + id),
                        "Не удалось получить расчёт за месяц с id " + id
                );
            } else {
                tempPayload = MONTH_DATA_CONTROLLER.getPrevMonthData(id).getPayload();
                prevMonthData = tempPayload instanceof MonthData ? (MonthData) tempPayload : null;

                if (prevMonthData == null) {
                    return ResponseUtil.buildError(
                            new IncorrectData("Не найдено предыдущего месяца для месяца с id " + id),
                            "Не удалось получить расчёт за месяц с id " + id
                    );
                }
            }

            TariffsData tariffs = monthData.getTariffsData();
            CountersData monthCountersData = monthData.getCountersData();
            CountersData prevMonthCountersData = prevMonthData.getCountersData();

            long coldwaterDelta = monthCountersData.getColdwater() - prevMonthCountersData.getColdwater();
            long hotwaterDelta = monthCountersData.getHotwater() - prevMonthCountersData.getHotwater();

            Communal communal = new Communal()
                    .setColdwater(coldwaterDelta * tariffs.getColdwater())
                    .setHotwater(hotwaterDelta * tariffs.getHotwater())
                    .setElectricity((monthCountersData.getElectricity() - prevMonthCountersData.getElectricity()) * tariffs.getElectricity())
                    .setDrainage((coldwaterDelta + hotwaterDelta) * tariffs.getDrainage())
                    .calcTotal();

            double allAdditional = 0d;
            double negativeAdditional = 0d;
            for (AdditionalData data : monthData.getAdditionalData()) {
                allAdditional += data.getAmount();
                if (data.getAmount() < 0d) {
                    negativeAdditional += data.getAmount();
                }
            }

            double totalWithCommunal = monthData.getRent() + communal.getTotal();

            Calculation calculation = new Calculation()
                    .setCommunal(communal)
                    .setTotalWithCommunal(totalWithCommunal)
                    .setTotalWithCommunalAndAdditional(totalWithCommunal + allAdditional)
                    .setTotalWithCommunalAndNegativeAdditional(totalWithCommunal + negativeAdditional);

            return new ApiResponse().setStatus(new Status().setCode(StatusCode.SUCCESS)).setPayload(calculation);
        } else {
            return ResponseUtil.buildError(
                    new IncorrectData("Не был передан id месяца для расчёта"),
                    "Не удалось получить расчёт за месяц"
            );
        }
    }
}
