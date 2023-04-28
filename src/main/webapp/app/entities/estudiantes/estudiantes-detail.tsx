import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './estudiantes.reducer';

export const EstudiantesDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const estudiantesEntity = useAppSelector(state => state.estudiantes.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="estudiantesDetailsHeading">
          <Translate contentKey="gradosApp.estudiantes.detail.title">Estudiantes</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{estudiantesEntity.id}</dd>
          <dt>
            <span id="nombreestudiante">
              <Translate contentKey="gradosApp.estudiantes.nombreestudiante">Nombreestudiante</Translate>
            </span>
          </dt>
          <dd>{estudiantesEntity.nombreestudiante}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="gradosApp.estudiantes.email">Email</Translate>
            </span>
          </dt>
          <dd>{estudiantesEntity.email}</dd>
          <dt>
            <span id="telefono">
              <Translate contentKey="gradosApp.estudiantes.telefono">Telefono</Translate>
            </span>
          </dt>
          <dd>{estudiantesEntity.telefono}</dd>
        </dl>
        <Button tag={Link} to="/estudiantes" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/estudiantes/${estudiantesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default EstudiantesDetail;
