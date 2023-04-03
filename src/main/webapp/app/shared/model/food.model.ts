export interface IFood {
  id?: number;
  name?: string | null;
  description?: string | null;
  price?: number | null;
}

export const defaultValue: Readonly<IFood> = {};
