import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/estudiantes">
        <Translate contentKey="global.menu.entities.estudiantes" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/ceremoniagrados">
        <Translate contentKey="global.menu.entities.ceremoniagrados" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/planestudios">
        <Translate contentKey="global.menu.entities.planestudios" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
