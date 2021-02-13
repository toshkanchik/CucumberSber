package ru.appline.sberFramework.managers;

import ru.appline.sberFramework.pages.*;

public class PageMngr {
    private static PageMngr pageMngr;
    private static StartPage startPage;
    private static MortgageCalcPage mortgagePage;
    private static MortgageCalcPage mortgageCalcPage;
    private static MortgageSubwindow mortgageSubwindow;

    private PageMngr(){}
    public static PageMngr getPageMngr(){
        if(pageMngr == null){
            pageMngr = new PageMngr();
        }
        return pageMngr;
    }

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    public MortgageCalcPage getStrahovaniePage() {
        if (mortgagePage == null) {
            mortgagePage = new MortgageCalcPage();
        }
        return mortgagePage;
    }

    public MortgageCalcPage getMortgageCalc() {
        if (mortgageCalcPage == null) {
            mortgageCalcPage = new MortgageCalcPage();
        }
        return mortgageCalcPage;
    }

    public MortgageSubwindow getMortgageSubwindow() {
        if (mortgageSubwindow == null) {
            mortgageSubwindow = new MortgageSubwindow();
        }
        return mortgageSubwindow;
    }
}
