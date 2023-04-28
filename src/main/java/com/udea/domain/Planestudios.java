package com.udea.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Planestudios.
 */
@Entity
@Table(name = "planestudios")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Planestudios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Max(value = 300)
    @Column(name = "programaid", nullable = false)
    private Integer programaid;

    @NotNull
    @Max(value = 100)
    @Column(name = "facultadid", nullable = false)
    private Integer facultadid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Planestudios id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProgramaid() {
        return this.programaid;
    }

    public Planestudios programaid(Integer programaid) {
        this.setProgramaid(programaid);
        return this;
    }

    public void setProgramaid(Integer programaid) {
        this.programaid = programaid;
    }

    public Integer getFacultadid() {
        return this.facultadid;
    }

    public Planestudios facultadid(Integer facultadid) {
        this.setFacultadid(facultadid);
        return this;
    }

    public void setFacultadid(Integer facultadid) {
        this.facultadid = facultadid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Planestudios)) {
            return false;
        }
        return id != null && id.equals(((Planestudios) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Planestudios{" +
            "id=" + getId() +
            ", programaid=" + getProgramaid() +
            ", facultadid=" + getFacultadid() +
            "}";
    }
}
