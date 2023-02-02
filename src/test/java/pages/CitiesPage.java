package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CitiesPage extends BasePage {
    public CitiesPage(WebDriver driver, WebDriverWait wait, Faker faker) {
        super(driver, wait, faker);
    }

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/a[3]")
    private WebElement myProfilePageButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button[1]")
    private WebElement adminPageButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button[2]")
    private WebElement logoutButton;

    @FindBy(id = "search")
    private WebElement searchField;

    @FindBy(className = "btnNewItem")
    private WebElement newItemButton;

    @FindBy(id = "name")
    private WebElement cityNameField;

    @FindBy(className = "btnSave")
    private WebElement saveButton;

    @FindBy(id = "delete")
    private WebElement deleteButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[5]/div/div/div[2]/button[2]")
    private WebElement confirmDeleteButton;


    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")
    private WebElement createdCityMessage;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr")
    private List<WebElement> citiesList;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr/td[2]")
    private List<WebElement> citiesNamesList;

    @FindBy(id = "edit")
    private WebElement editButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div/main/div/div[2]/div/div[1]/div[3]/div[2]")
    private WebElement tablePagination;

    @FindBy(xpath = "//*[@id=\"app\"]/div/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")
    private WebElement message;

    public int getTableSize() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit")));
        return citiesList.size();
    }

    public String getCities(){
        String citiesNames = "";
        for (int i = 0; i < citiesNamesList.size(); i++){
            citiesNames += citiesNamesList.get(i).getText() + "/n";
        }
        return citiesNames;
    }

    public String getFirstCity(){
        String firstCity = "";
        for (int i = 0; i < citiesNamesList.size(); i++){
            firstCity = citiesNamesList.get(0).getText();
        }
        return firstCity;
    }

    public void addCity(String city) {
        newItemButton.click();
        cityNameField.sendKeys(city);
        saveButton.click();
    }

    public String getAddedCityMessage() {
        return createdCityMessage.getText();
    }

    public void editCity(String city) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit")));
        for (int i = 0; i < citiesList.size(); i++) {
            if (citiesList.get(i).getText().contains(city)) {
                editButton.click();
                cityNameField.sendKeys("- edited");
                saveButton.click();
            }
        }
    }

    public void searchCity(String city){
        searchField.sendKeys(city);
    }

    public String getTablePagination(){
        return tablePagination.getText();
    }

    public void deleteCity(){
        deleteButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[5]/div/div/div[2]/button[2]")));
        confirmDeleteButton.click();
    }

    public String getMessage(){
        return message.getText();
    }
}
