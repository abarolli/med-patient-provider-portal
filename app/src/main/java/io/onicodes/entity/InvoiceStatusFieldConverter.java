package io.onicodes.entity;

import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class InvoiceStatusFieldConverter extends EnumDBFieldConverter<InvoicePaymentStatus> {
    
    private InvoiceStatusFieldConverter() {
        super(InvoicePaymentStatus.class);
    }
}
