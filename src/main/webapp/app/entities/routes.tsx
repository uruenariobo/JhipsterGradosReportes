import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Estudiantes from './estudiantes';
import Ceremoniagrados from './ceremoniagrados';
import Planestudios from './planestudios';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="estudiantes/*" element={<Estudiantes />} />
        <Route path="ceremoniagrados/*" element={<Ceremoniagrados />} />
        <Route path="planestudios/*" element={<Planestudios />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
