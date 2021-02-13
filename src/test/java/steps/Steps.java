package steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.appline.sberFramework.managers.PageMngr;

public class Steps {
    private PageMngr app = PageMngr.getPageMngr();

    @Когда("^Загружена стартовая страница$")
    public void getInitialPage(){app.getStartPage();}

    @Когда("^Выбор меню '(.*)'$")
    public void selectNameBaseMenu(String nameBaseMenu){app.getStartPage().selectBaseMenu(nameBaseMenu);    }

    @Когда("^Выбор подменю '(.*)'$")
    public void selectNameSubMenu(String nameSubMenu){app.getStartPage().selectSubMenu(nameSubMenu);}

    @Когда("^Переключиться на внутреннее окно$")
    public void switchToSubwindow(){app.getMortgageCalc().switchToSubwindow();}

    @Когда("^Ввести в окошко '(.*)' параметр '(.*)'$")
    public void inputNumbers(String name, String value){app.getMortgageSubwindow().inputField(name, value);}

    @Когда("^Поставить галочку '(.*)' в позицию '(.*)'$")
    public void inputBools(String name, Boolean value){app.getMortgageSubwindow().setButtonsByName(name, value);}

    @Тогда("^Проверить что поле '(.*)' имеет значение '(.*)'$")
    public void checkField(String name, String value){app.getMortgageSubwindow().checkIfFieldEquals(name, value);}

}
