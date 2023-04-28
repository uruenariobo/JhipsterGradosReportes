import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './ceremoniagrados.reducer';

export const CeremoniagradosDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const ceremoniagradosEntity = useAppSelector(state => state.ceremoniagrados.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="ceremoniagradosDetailsHeading">
          <Translate contentKey="gradosApp.ceremoniagrados.detail.title">Ceremoniagrados</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{ceremoniagradosEntity.id}</dd>
          <dt>
            <span id="fechaceremonia">
              <Translate contentKey="gradosApp.ceremoniagrados.fechaceremonia">Fechaceremonia</Translate>
            </span>
          </dt>
          <dd>
            {ceremoniagradosEntity.fechaceremonia ? (
              <TextFormat value={ceremoniagradosEntity.fechaceremonia} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="limiteinscripcion">
              <Translate contentKey="gradosApp.ceremoniagrados.limiteinscripcion">Limiteinscripcion</Translate>
            </span>
          </dt>
          <dd>
            {ceremoniagradosEntity.limiteinscripcion ? (
              <TextFormat value={ceremoniagradosEntity.limiteinscripcion} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="lugar">
              <Translate contentKey="gradosApp.ceremoniagrados.lugar">Lugar</Translate>
            </span>
          </dt>
          <dd>{ceremoniagradosEntity.lugar}</dd>
        </dl>
        <Button tag={Link} to="/ceremoniagrados" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ceremoniagrados/${ceremoniagradosEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CeremoniagradosDetail;
