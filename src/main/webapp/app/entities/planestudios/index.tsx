import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Planestudios from './planestudios';
import PlanestudiosDetail from './planestudios-detail';
import PlanestudiosUpdate from './planestudios-update';
import PlanestudiosDeleteDialog from './planestudios-delete-dialog';

const PlanestudiosRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Planestudios />} />
    <Route path="new" element={<PlanestudiosUpdate />} />
    <Route path=":id">
      <Route index element={<PlanestudiosDetail />} />
      <Route path="edit" element={<PlanestudiosUpdate />} />
      <Route path="delete" element={<PlanestudiosDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default PlanestudiosRoutes;
