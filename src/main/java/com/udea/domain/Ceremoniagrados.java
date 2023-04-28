package com.udea.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Ceremoniagrados.
 */
@Entity
@Table(name = "ceremoniagrados")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Ceremoniagrados implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "fechaceremonia", nullable = false)
    private LocalDate fechaceremonia;

    @NotNull
    @Column(name = "limiteinscripcion", nullable = false)
    private LocalDate limiteinscripcion;

    @NotNull
    @Size(max = 20)
    @Column(name = "lugar", length = 20, nullable = false)
    private String lugar;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Ceremoniagrados id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaceremonia() {
        return this.fechaceremonia;
    }

    public Ceremoniagrados fechaceremonia(LocalDate fechaceremonia) {
        this.setFechaceremonia(fechaceremonia);
        return this;
    }

    public void setFechaceremonia(LocalDate fechaceremonia) {
        this.fechaceremonia = fechaceremonia;
    }

    public LocalDate getLimiteinscripcion() {
        return this.limiteinscripcion;
    }

    public Ceremoniagrados limiteinscripcion(LocalDate limiteinscripcion) {
        this.setLimiteinscripcion(limiteinscripcion);
        return this;
    }

    public void setLimiteinscripcion(LocalDate limiteinscripcion) {
        this.limiteinscripcion = limiteinscripcion;
    }

    public String getLugar() {
        return this.lugar;
    }

    public Ceremoniagrados lugar(String lugar) {
        this.setLugar(lugar);
        return this;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ceremoniagrados)) {
            return false;
        }
        return id != null && id.equals(((Ceremoniagrados) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ceremoniagrados{" +
            "id=" + getId() +
            ", fechaceremonia='" + getFechaceremonia() + "'" +
            ", limiteinscripcion='" + getLimiteinscripcion() + "'" +
            ", lugar='" + getLugar() + "'" +
            "}";
    }
}
