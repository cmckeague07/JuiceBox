package actions;

import net.thucydides.core.annotations.Step;
import actions.AddressPage;

public class AddressActions {
    AddressPage addressPage;

    @Step("Open checkout page")
    public void openCheckout() {
        addressPage.open();
    }

    @Step("Add new address")
    public void addNewAddress(String country, String name, String mobile, String zip, String address, String city, String state) {
        addressPage.addNewAddress(country, name, mobile, zip, address, city, state);
    }


    @Step("Select newly added address")
    public void selectLatestAddress() {
        addressPage.selectLatestAddress();
    }
}
