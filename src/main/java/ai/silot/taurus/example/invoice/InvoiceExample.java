package ai.silot.taurus.example.invoice;

import ai.silot.taurus.config.Taurus;
import ai.silot.taurus.model.TaurusBaseVo;
import ai.silot.taurus.model.TaurusListVo;
import ai.silot.taurus.model.invoice.InvoiceVo;
import ai.silot.taurus.service.InvoiceService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class InvoiceExample {

    public static void main(String[] args) throws IOException {
        Taurus.apiKey = "dcc99ba6-3b2f-479b-9f85-86a09ccaaacf";
        Taurus.serverUrl = "http://k8s-azure.silot.tech:30331";
        // create example
        createExample();
        // inquiry example
        getByIdExample();
        // list example
        listExample();
        // expire example
        expireExample();
    }

    public static void createExample() throws IOException {
        String externalId = "523452303483";
        BigDecimal amount = new BigDecimal(16566);
        String description = "test create";
        Integer invoiceDuration = 86400;
        String successRedirectUrl = "http://example.com/success/callback";

        TaurusBaseVo<InvoiceVo> responseVo = InvoiceService.create(
                externalId, amount, description, invoiceDuration, successRedirectUrl);
        InvoiceVo invoiceVo = responseVo.getData();
        System.out.println(invoiceVo.getInvoiceId());
    }

    public static void getByIdExample() throws IOException {
        Long invoiceId = 1373843417495793665L;
        TaurusBaseVo<InvoiceVo> responseVo = InvoiceService.getById(invoiceId);
        InvoiceVo invoiceVo = responseVo.getData();
        System.out.println(invoiceVo.getInvoiceId());
    }

    public static void listExample() throws IOException {
        int limit = 2;
        TaurusBaseVo<TaurusListVo<InvoiceVo>> responseVo = InvoiceService.list(limit, null, null);
        List<InvoiceVo> list = responseVo.getData().getList();
        System.out.println(list.get(0).getInvoiceId());
    }

    public static void expireExample() throws IOException {
        Long invoiceId = 1373843417495793665L;
        TaurusBaseVo<Object> expireVo = InvoiceService.expire(invoiceId);
        System.out.println(expireVo.getCode());
    }

}
