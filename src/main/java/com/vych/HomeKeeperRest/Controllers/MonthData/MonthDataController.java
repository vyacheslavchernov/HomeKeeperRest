package com.vych.HomeKeeperRest.Controllers.MonthData;

import com.vych.HomeKeeperRest.ApiCore.*;
import com.vych.HomeKeeperRest.ApiCore.Exceptions.IncorrectData;
import com.vych.HomeKeeperRest.ApiCore.Exceptions.InternalError;
import com.vych.HomeKeeperRest.ApiCore.Payloads.ListPayload;
import com.vych.HomeKeeperRest.ApiCore.Payloads.StringPayload;
import com.vych.HomeKeeperRest.Controllers.MonthData.RequestBody.Month;
import com.vych.HomeKeeperRest.Domain.MonthData.AdditionalData;
import com.vych.HomeKeeperRest.Domain.MonthData.MonthData;
import com.vych.HomeKeeperRest.Repo.MonthDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Контроллер управляющий данными по месяцам
 */
@RestController
public class MonthDataController {

    private final String CONTROLLER_ENDPOINT = "api/monthdata/";

    private final MonthDataRepo MONTH_DATA_REPO;

    @Autowired
    public MonthDataController(MonthDataRepo monthDataRepo) {
        this.MONTH_DATA_REPO = monthDataRepo;
    }


    @GetMapping(CONTROLLER_ENDPOINT + "getall")
    public ApiResponse getAllMonthData() {
        ArrayList<MonthData> monthData;

        try {
            monthData = (ArrayList<MonthData>) MONTH_DATA_REPO.findAll();
        } catch (Exception e) {
            return ResponseUtil.buildError(e, "Ошибка при попытке получить данные за все месяца");
        }

        return new ApiResponse()
                .setStatus(new Status().setCode(StatusCode.SUCCESS))
                .setPayload(new ListPayload().setListOfPayload(Collections.singletonList(monthData)));
    }

    @GetMapping(CONTROLLER_ENDPOINT + "get")
    public ApiResponse getMonthData(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer year
    ) {
        if (id != null) {
            MonthData monthData = MONTH_DATA_REPO.findById(id).orElse(null);
            if (monthData == null ) {
                return ResponseUtil.buildError(
                        new IncorrectData("Не найдено месяца с id " + id),
                        "Не удалось получить данные за месяц"
                );
            } else {
                return new ApiResponse().setStatus(new Status().setCode(StatusCode.SUCCESS)).setPayload(monthData);
            }
        } else if (month != null && year != null) {
            MonthData monthData = MONTH_DATA_REPO.findByMonthAndYear(month, year).orElse(null);
            if (monthData == null ) {
                return ResponseUtil.buildError(
                        new IncorrectData("Не найдено данных по паре месяц-год " + month + "-" + year),
                        "Не удалось получить данные за месяц"
                );
            } else {
                return new ApiResponse().setStatus(new Status().setCode(StatusCode.SUCCESS)).setPayload(monthData);
            }
        } else {
            return ResponseUtil.buildError(
                    new IncorrectData("Не был передан id или месяц и год для удаления данных"),
                    "Не удалось получить данные за месяц"
            );
        }
    }

    @GetMapping(CONTROLLER_ENDPOINT + "getlast")
    public ApiResponse getLastMonthData() {
        MonthData monthData = MONTH_DATA_REPO.findLastMonth().orElse(null);
        if (monthData == null ) {
            return ResponseUtil.buildError(
                    new InternalError("Не удалось корректно выполнить запрос к БД для получения последнего месяца (Месяц не найден)"),
                    "Не удалось получить данные за последний месяц"
            );
        } else {
            return new ApiResponse().setStatus(new Status().setCode(StatusCode.SUCCESS)).setPayload(monthData);
        }
    }

    @PostMapping(CONTROLLER_ENDPOINT + "add")
    public ApiResponse addNewMonthData(@RequestBody MonthData monthData) {
        try {
            MONTH_DATA_REPO.save(monthData);
        } catch (Exception e) {
            return ResponseUtil.buildError(e, "Ошибка при попытке добавить данные за месяц");
        }

        return ResponseUtil.buildSuccess();
    }

    @PostMapping(CONTROLLER_ENDPOINT + "delete")
    public ApiResponse deleteMonthData(@RequestBody Month month) {
        if (month.getId() != null) {
            MonthData monthData = MONTH_DATA_REPO.findById(month.getId()).orElse(null);
            if (monthData == null ) {
                return ResponseUtil.buildError(
                        new IncorrectData("Не найдено месяца с id " + month.getId()),
                        "Не удалось удалить данные за месяц"
                );
            } else {
                MONTH_DATA_REPO.delete(monthData);
            }
        } else if (month.getMonth() != null && month.getYear() != null) {
            MonthData monthData = MONTH_DATA_REPO.findByMonthAndYear(month.getMonth(), month.getYear()).orElse(null);
            if (monthData == null ) {
                return ResponseUtil.buildError(
                        new IncorrectData("Не найдено данных по паре месяц-год " + month.getMonth() + "-" + month.getYear()),
                        "Не удалось удалить данные за месяц"
                );
            } else {
                MONTH_DATA_REPO.delete(monthData);
            }
        } else {
            return ResponseUtil.buildError(
                    new IncorrectData("Не был передан id или месяц и год для удаления данных"),
                    "Не удалось удалить данные за месяц"
            );
        }

        return ResponseUtil.buildSuccess();
    }

    @PostMapping(CONTROLLER_ENDPOINT + "update")
    public ApiResponse updateMonthData(@RequestBody MonthData monthData) {
        if (monthData.getId() == null) {
            return ResponseUtil.buildError(
                    new IncorrectData("Не заполнен id месяца"),
                    "Не удалось обновить данные за месяц"
            );
        }

        if (MONTH_DATA_REPO.findById(monthData.getId()).orElse(null) == null) {
            return ResponseUtil.buildError(
                    new IncorrectData("Месяца с id " + monthData.getId() + " не существует"),
                    "Не удалось обновить данные за месяц"
            );
        }

        //TODO: Добавить проверку на существование записи о тарифах по аналогии с записью о месяце
        if (monthData.getTariffsData().getId() == null) {
            return ResponseUtil.buildError(
                    new IncorrectData("Не заполнен id для тарифов"),
                    "Не удалось обновить данные за месяц"
            );
        }

        //TODO: Добавить проверку на существование записи о счётчиках по аналогии с записью о месяце
        if (monthData.getCountersData().getId() == null) {
            return ResponseUtil.buildError(
                    new IncorrectData("Не заполнен id для счётчиков"),
                    "Не удалось обновить данные за месяц"
            );
        }

        //TODO: Добавить проверку на существование записи с доп. информацией по аналогии с записью о месяце
        for(AdditionalData data : monthData.getAdditionalData()) {
            if (data.getId() == null) {
                return ResponseUtil.buildError(
                        new IncorrectData("Не заполнен id для одного или нескольких блоков с доп. данными"),
                        "Не удалось обновить данные за месяц"
                );
            }
        }

        try {
            MONTH_DATA_REPO.save(monthData);
        } catch (Exception e) {
            return ResponseUtil.buildError(e, "Ошибка при попытке обновить данные за все месяца");
        }

        return new ApiResponse()
                .setStatus(new Status().setCode(StatusCode.SUCCESS))
                .setPayload(new ListPayload()
                        .addItem(new StringPayload().setMessage("Данные успешно обновлены"))
                        .addItem(monthData)
                );
    }
}
