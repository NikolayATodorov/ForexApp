import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: 23772,
  login: 'KU33`@4',
};

export const sampleWithPartialData: IUser = {
  id: 180,
  login: 'BW3XBk',
};

export const sampleWithFullData: IUser = {
  id: 30955,
  login: 'ht',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
