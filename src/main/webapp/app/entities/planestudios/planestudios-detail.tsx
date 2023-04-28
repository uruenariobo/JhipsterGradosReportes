import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './planestudios.reducer';

export const PlanestudiosDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const planestudiosEntity = useAppSelector(state => state.planestudios.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="planestudiosDetailsHeading">
          <Translate contentKey="gradosApp.planestudios.detail.title">Planestudios</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{planestudiosEntity.id}</dd>
          <dt>
            <span id="programaid">
              <Translate contentKey="gradosApp.planestudios.programaid">Programaid</Translate>
            </span>
          </dt>
          <dd>{planestudiosEntity.programaid}</dd>
          <dt>
            <span id="facultadid">
              <Translate contentKey="gradosApp.planestudios.facultadid">Facultadid</Translate>
            </span>
          </dt>
          <dd>{planestudiosEntity.facultadid}</dd>
        </dl>
        <Button tag={Link} to="/planestudios" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/planestudios/${planestudiosEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PlanestudiosDetail;
