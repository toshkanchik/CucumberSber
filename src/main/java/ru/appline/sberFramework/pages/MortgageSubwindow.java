package ru.appline.sberFramework.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.List;

public class MortgageSubwindow extends BasePage {
    @FindBy(xpath = "//div[@class='dc-input__input-container-4-9-1']")
    List<WebElement> InputFields;

    @FindBy(xpath = "//div[@data-e2e-id='mland-calculator/discounts-block']")
    WebElement discounts;

    @FindBy(xpath = "//div[@data-test-id='main-results-block']")
    WebElement resultsBlock;


    /**
     * Функция заполнения числовых полей
     *
     * @param field - наименование поля
     * @param value - желаемое значение
     * @return MortgageSubwindow
     */
    @Step("Заполнить поле '{field}' значением '{value}'")
    public MortgageSubwindow inputField(String field, String value) {

        DecimalFormat df = new DecimalFormat();
        df.setGroupingUsed(true);
        df.setGroupingSize(3);
        DecimalFormatSymbols decimalFormatSymbols = df.getDecimalFormatSymbols();
        decimalFormatSymbols.setGroupingSeparator(' ');
        df.setDecimalFormatSymbols(decimalFormatSymbols);

        for (WebElement element : InputFields) {
            if (element.findElement(By.xpath("./div")).getText().equalsIgnoreCase(field)) {
                WebElement input = element.findElement(By.xpath("./input"));
                if(!isVisibleInViewport(input)) scrollToElementOffsetJs(input);
//                scrollToElementOffsetJs(input);
                elementToBeClickable(input).click();
                input.sendKeys(Keys.CONTROL, "a");
                input.sendKeys(value, Keys.ENTER);
                Assertions.assertEquals(input.getAttribute("value"), df.format(Integer.parseInt(value.replaceAll(
                        "[^\\d]", ""))), "При вводе значение произошла ошибка");
                waitUntilPricesFinalize();
                break;
            }
        }
        return this;
    }

    /**
     * Функция выставления галочек
     *
     * @param name - наименование поля
     * @param shouldBeChecked - желаемое значение
     * @return MortgageSubwindow
     */
    @Step("Заполнить поле '{name}' значением '{shouldBeChecked}'")
    public MortgageSubwindow setButtonsByName(String name, boolean shouldBeChecked){
        String shouldBe = String.valueOf(shouldBeChecked);
        WebElement currElement = discounts.findElement(By.xpath("//span[. = '" + name + "']/../..//input"));
        String isChecked = currElement.getAttribute("aria-checked");
        if(!shouldBe.equalsIgnoreCase(isChecked)){
//            if(!isVisibleInViewport(currElement)) scrollToElementOffsetJs(currElement);
            scrollToElementOffsetJs(currElement);
            currElement.click();
        }
        wait.until(ExpectedConditions.attributeContains(currElement, "aria-checked", shouldBe));
        waitUntilPricesFinalize();
        return this;
    }

    /**
     * Функция ожидания скрипта перемотки цен. Использует поле цены "Ежемесячный платёж"
     *
     */
    public void waitUntilPricesFinalize(){
        WebElement tmpElement = resultsBlock
                .findElement(By.xpath(".//span[@data-e2e-id]"));
        String tmp = tmpElement.getText();
        Date date = new Date();
        long initTime = date.getTime();
        while(true)
        {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Assertions.fail("Ошибка при ожидании окончания скрипта отмотки цен");
            }
            if(tmp.equals(tmpElement.getText())) break;
            tmp = tmpElement.getText();
            date = new Date();
            if((date.getTime() - initTime) > 5000) Assertions.fail("Бесконечный цикл в скрипте изменения цен");
        }
    }

    /**
     * Функция проверки поля
     *
     * @param name - наименование поля
     * @param shouldBe - желаемое значение
     * @return MortgageSubwindow
     */
    @Step("Проверка что в поле '{name}' значение '{shouldBe}'")
    public MortgageSubwindow checkIfFieldEquals(String name, String shouldBe){
        WebElement currElement = resultsBlock
                .findElement(By.xpath(".//span[. = '" + name + "']/../span[@data-e2e-id]/span"));
        scrollToElementJs(resultsBlock);
        Assertions.assertEquals(currElement.getText(), shouldBe,"Значение поля '" + name +" не совпадает'");
        return this;
    }

}
