package Tests;

import Pages.Allocate;
import Pages.Login;
import Pages.PodsPlatform;
import org.testng.annotations.Test;

public class AllocationPageTest extends BaseClass {
    Allocate AllocationOwner = new Allocate();
    Login login = new Login();
    PodsPlatform PO = new PodsPlatform();

    public void create_newpod() throws InterruptedException {
        Thread.sleep(7000);

        PO.click_test_btn()
                .click_edit_btn()
                .set_delivery_leader()
                .set_product_manager()
                .click_update_btn()
                .wait_for_message()
                .go_back()
                .click_new_config()
                .set_config_name()
                .click_config_start_date()
                .set_todays_date()
                .set_role()
                .set_skill()
                .click_save_config()
                .wait_for_message()
                .click_new_pod()
                .set_pod_name()
                .click_onboard_date()
                .set_onboard_start_date()
                .click_billing_date()
                .set_billing_start_date()
                .set_SKU()
                .click_confirm_pod_btn()
                .select_config()
                .click_continue_btn()
                .click_confirm_btn()
                .wait_for_message()
                .click_latest_created_pod()
                .click_move_to_allocation()
                .click_confirm_btn();
    }

    @Test
    public void TC07_VerifyStatusOfPod() throws InterruptedException {
        login.login_to_website();
        create_newpod();
        login.go_to_allocation_engine();

        AllocationOwner.verify_StatusOfPod();

    }
    @Test
    public void TC08_VerifyStatusOfPodAfterNominate() throws InterruptedException {
        login.login_to_website();
        create_newpod();
        login.go_to_allocation_engine();

        AllocationOwner.click_pod(PO.pod_id).
                click_nominate_btn().
                click_add_hasher().
                click_ConfirmNomination().
                click_allocationsTab().
                click_Confirm().
                verify_StatusAfterNominate();

    }
    @Test
    public void TC09_VerifyAddingHaSher() throws InterruptedException {
        login.login_to_website();
        create_newpod();
        login.go_to_allocation_engine();

        AllocationOwner.click_pod(PO.pod_id).
                click_nominate_btn().
                click_add_hasher().
                click_ConfirmNomination();
    }
    @Test
    public void TC10_VerifyFilterOption() throws InterruptedException {
        login.login_to_website();
        create_newpod();
        login.go_to_allocation_engine();

        AllocationOwner.click_pod(PO.pod_id).
                click_nominate_btn().
                click_filter().
                click_apply().
                Verify_TheFilter();

    }
    @Test
    public void TC21_verifyStatusAfterSaveConfiguration() throws InterruptedException{
        login.login_to_website();
        create_newpod();
        login.go_to_allocation_engine();

        AllocationOwner.click_UpcomingPods().
                verify_StatusAfterConfigureThePod();
    }


}
