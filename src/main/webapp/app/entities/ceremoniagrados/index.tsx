import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Ceremoniagrados from './ceremoniagrados';
import CeremoniagradosDetail from './ceremoniagrados-detail';
import CeremoniagradosUpdate from './ceremoniagrados-update';
import CeremoniagradosDeleteDialog from './ceremoniagrados-delete-dialog';

const CeremoniagradosRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Ceremoniagrados />} />
    <Route path="new" element={<CeremoniagradosUpdate />} />
    <Route path=":id">
      <Route index element={<CeremoniagradosDetail />} />
      <Route path="edit" element={<CeremoniagradosUpdate />} />
      <Route path="delete" element={<CeremoniagradosDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CeremoniagradosRoutes;
