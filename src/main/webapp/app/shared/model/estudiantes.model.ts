export interface IEstudiantes {
  id?: number;
  nombreestudiante?: string;
  email?: string;
  telefono?: string;
}

export const defaultValue: Readonly<IEstudiantes> = {};
