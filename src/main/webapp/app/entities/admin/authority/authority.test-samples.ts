import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: '06d22066-e8b6-45a9-8fa2-cdcf218466c7',
};

export const sampleWithPartialData: IAuthority = {
  name: '5db9bfb8-c576-481e-8db1-764349139fa7',
};

export const sampleWithFullData: IAuthority = {
  name: 'a50ffc33-15ad-440f-af24-77af1f922e9c',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
