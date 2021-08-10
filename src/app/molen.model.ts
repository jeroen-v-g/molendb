export interface Molen {
  molenId?: number;
  naam: string;
  bouwjaar?: number;
  type: string;
  kenmerken: string;
  functie: string;
  adres: string;
  molenaar: string;
  eigenaar: string;
  plaats: string;
  website: string;
  postPhotoUrl?: string;
  isBeingModified?: boolean;
  fotoWidth?: number;
  fotoHeight?: number;
}
