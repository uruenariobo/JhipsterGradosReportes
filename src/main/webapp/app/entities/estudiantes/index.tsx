import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Estudiantes from './estudiantes';
import EstudiantesDetail from './estudiantes-detail';
import EstudiantesUpdate from './estudiantes-update';
import EstudiantesDeleteDialog from './estudiantes-delete-dialog';

const EstudiantesRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Estudiantes />} />
    <Route path="new" element={<EstudiantesUpdate />} />
    <Route path=":id">
      <Route index element={<EstudiantesDetail />} />
      <Route path="edit" element={<EstudiantesUpdate />} />
      <Route path="delete" element={<EstudiantesDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default EstudiantesRoutes;
