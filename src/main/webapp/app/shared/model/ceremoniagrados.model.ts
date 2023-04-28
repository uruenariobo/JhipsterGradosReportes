import dayjs from 'dayjs';

export interface ICeremoniagrados {
  id?: number;
  fechaceremonia?: string;
  limiteinscripcion?: string;
  lugar?: string;
}

export const defaultValue: Readonly<ICeremoniagrados> = {};
