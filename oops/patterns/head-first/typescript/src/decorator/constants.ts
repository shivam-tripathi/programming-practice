export enum SIZES {
  TALL = 'TALL',
  GRANDE = 'GRANDE',
  LARGE = 'LARGE',
}

type PricingMapType = {
  [key in SIZES]: number;
};

type PricingType = {
  CONDIMENTS: {
    SOY: PricingMapType;
    MOCHA: PricingMapType;
    STEAMED_MILK: PricingMapType;
    WHIP: PricingMapType;
  };
  BEVERAGES: {
    DARK_ROAST: PricingMapType;
    DECAF: PricingMapType;
    EXPRESSO: PricingMapType;
    HOUSE_BLEND: PricingMapType;
  };
};

export const PRICING: PricingType = {
  CONDIMENTS: {
    SOY: {
      [SIZES.TALL]: 0.15,
      [SIZES.GRANDE]: 0.2,
      [SIZES.LARGE]: 0.25,
    },
    MOCHA: {
      [SIZES.TALL]: 0.2,
      [SIZES.GRANDE]: 0.25,
      [SIZES.LARGE]: 0.3,
    },
    STEAMED_MILK: {
      [SIZES.TALL]: 0.1,
      [SIZES.GRANDE]: 0.15,
      [SIZES.LARGE]: 0.2,
    },
    WHIP: {
      [SIZES.TALL]: 0.1,
      [SIZES.GRANDE]: 0.15,
      [SIZES.LARGE]: 0.2,
    },
  },
  BEVERAGES: {
    DARK_ROAST: {
      [SIZES.TALL]: 0.99,
      [SIZES.GRANDE]: 1.49,
      [SIZES.LARGE]: 1.99,
    },
    DECAF: {
      [SIZES.TALL]: 1.05,
      [SIZES.GRANDE]: 1.55,
      [SIZES.LARGE]: 2.05,
    },
    EXPRESSO: {
      [SIZES.TALL]: 1.99,
      [SIZES.GRANDE]: 2.49,
      [SIZES.LARGE]: 2.99,
    },
    HOUSE_BLEND: {
      [SIZES.TALL]: 0.89,
      [SIZES.GRANDE]: 1.39,
      [SIZES.LARGE]: 1.89,
    },
  },
};
