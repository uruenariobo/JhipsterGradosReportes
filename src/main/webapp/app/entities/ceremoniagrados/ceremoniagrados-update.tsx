import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICeremoniagrados } from 'app/shared/model/ceremoniagrados.model';
import { getEntity, updateEntity, createEntity, reset } from './ceremoniagrados.reducer';

export const CeremoniagradosUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const ceremoniagradosEntity = useAppSelector(state => state.ceremoniagrados.entity);
  const loading = useAppSelector(state => state.ceremoniagrados.loading);
  const updating = useAppSelector(state => state.ceremoniagrados.updating);
  const updateSuccess = useAppSelector(state => state.ceremoniagrados.updateSuccess);

  const handleClose = () => {
    navigate('/ceremoniagrados' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...ceremoniagradosEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...ceremoniagradosEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gradosApp.ceremoniagrados.home.createOrEditLabel" data-cy="CeremoniagradosCreateUpdateHeading">
            <Translate contentKey="gradosApp.ceremoniagrados.home.createOrEditLabel">Create or edit a Ceremoniagrados</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="ceremoniagrados-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('gradosApp.ceremoniagrados.fechaceremonia')}
                id="ceremoniagrados-fechaceremonia"
                name="fechaceremonia"
                data-cy="fechaceremonia"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('gradosApp.ceremoniagrados.limiteinscripcion')}
                id="ceremoniagrados-limiteinscripcion"
                name="limiteinscripcion"
                data-cy="limiteinscripcion"
                type="date"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('gradosApp.ceremoniagrados.lugar')}
                id="ceremoniagrados-lugar"
                name="lugar"
                data-cy="lugar"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/ceremoniagrados" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default CeremoniagradosUpdate;
