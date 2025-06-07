package io.onicodes.entity;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Entity
@Table(name = "invoices")
public class Invoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false, unique = true)
    private Appointment appointment;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amountDue;

    @Convert(converter = InvoiceStatusFieldConverter.class)
    @Column(nullable = false)
    private InvoicePaymentStatus status = InvoicePaymentStatus.PENDING;

    // cascading should always happend from parent to child, not the other way around
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice", orphanRemoval = true)
    private Set<InvoiceItem> invoiceItems;
}