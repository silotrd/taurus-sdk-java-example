package ai.silot.taurus.example.disbursement;

import ai.silot.taurus.config.Taurus;
import ai.silot.taurus.model.TaurusBaseVo;
import ai.silot.taurus.model.TaurusListVo;
import ai.silot.taurus.model.disbursement.DisbursementVo;
import ai.silot.taurus.service.DisbursementService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class DisbursementExample {

    public static void main(String[] args) throws IOException {
        Taurus.apiKey = "dcc99ba6-3b2f-479b-9f85-86a09ccaaacf";
        Taurus.serverUrl = "http://k8s-azure.silot.tech:30331";
        // create example
        createExample();
        // inquiry example
        getByIdExample();
        // list example
        listExample();
    }

    public static void createExample() throws IOException {
        String externalId = "1341802490800852001";
        BigDecimal amount = new BigDecimal("10000");
        String bankCode = "014";
        String accountHolderName = "Test";
        String accountNumber = "1234567890";
        String description = "Test Disbursement";

        TaurusBaseVo<DisbursementVo> responseVo = DisbursementService.create(
                externalId, amount, bankCode, accountHolderName, accountNumber, description);
        DisbursementVo disbursementVo = responseVo.getData();
        System.out.println(disbursementVo.getDisbursementId());
    }

    public static void getByIdExample() throws IOException {
        Long disbursementId = 1353544152879497217L;
        TaurusBaseVo<DisbursementVo> responseVo = DisbursementService.getById(disbursementId);
        DisbursementVo disbursementVo = responseVo.getData();
        System.out.println(disbursementVo.getDisbursementId());
    }

    public static void listExample() throws IOException {
        int limit = 2;
        TaurusBaseVo<TaurusListVo<DisbursementVo>> responseVo = DisbursementService.list(limit, null, null);
        List<DisbursementVo> list = responseVo.getData().getList();
        System.out.println(list.get(0).getDisbursementId());
    }
}
