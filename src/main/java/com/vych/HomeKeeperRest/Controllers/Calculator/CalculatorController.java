package com.vych.HomeKeeperRest.Controllers.Calculator;

import com.vych.HomeKeeperRest.ApiCore.ApiResponse;
import com.vych.HomeKeeperRest.ApiCore.Exceptions.IncorrectData;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ResponsePayload;
import com.vych.HomeKeeperRest.ApiCore.ResponseUtil;
import com.vych.HomeKeeperRest.ApiCore.Status;
import com.vych.HomeKeeperRest.ApiCore.StatusCode;
import com.vych.HomeKeeperRest.Aspects.Annotations.NeedLogs;
import com.vych.HomeKeeperRest.Controllers.Calculator.ResponseBody.Calculation;
import com.vych.HomeKeeperRest.Controllers.Calculator.ResponseBody.Communal;
import com.vych.HomeKeeperRest.Controllers.MonthData.MonthDataController;
import com.vych.HomeKeeperRest.Domain.MonthData.AdditionalData;
import com.vych.HomeKeeperRest.Domain.MonthData.CountersData;
import com.vych.HomeKeeperRest.Domain.MonthData.MonthData;
import com.vych.HomeKeeperRest.Domain.MonthData.TariffsData;
import com.vych.HomeKeeperRest.Repo.MonthData.AdditionalDataRepo;
import com.vych.HomeKeeperRest.Repo.MonthData.CountersDataRepo;
import com.vych.HomeKeeperRest.Repo.MonthData.MonthDataRepo;
import com.vych.HomeKeeperRest.Repo.MonthData.TariffsDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    private final String CONTROLLER_ENDPOINT = "api/calculator/";

    private final MonthDataController MONTH_DATA_CONTROLLER;

    @Autowired
    public CalculatorController(
            MonthDataRepo monthDataRepo,
            CountersDataRepo countersDataRepo,
            TariffsDataRepo tariffsDataRepo,
            AdditionalDataRepo additionalDataRepo
    ) {
        MONTH_DATA_CONTROLLER = new MonthDataController(
                monthDataRepo,
                countersDataRepo,
                tariffsDataRepo,
                additionalDataRepo
        );
    }

    @NeedLogs
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
