export const ACCESS_TOKEN = 'Access-Token';
export const REFRESH_TOKEN = 'Refresh-Token';
export const CREATE_AT = 'Create-At';
// export const EXPIRATION_ACCESS_TOKEN = 30;
// export const EXPIRATION_REFRESH_TOKEN = 60;
export const EXPIRATION_ACCESS_TOKEN = 60 * 60;
export const EXPIRATION_REFRESH_TOKEN = 7 * 24 * 60 * 60;
export const TOKEN_PREFIX = 'Bearer ';

export const SIDEBAR_TYPE = 'sidebar_type';
export const TOGGLE_MOBILE_TYPE = 'is_mobile';
export const TOGGLE_NAV_THEME = 'nav_theme';
export const TOGGLE_LAYOUT = 'layout';
export const TOGGLE_FIXED_HEADER = 'fixed_header';
export const TOGGLE_FIXED_SIDEBAR = 'fixed_sidebar';
export const TOGGLE_CONTENT_WIDTH = 'content_width';
export const TOGGLE_HIDE_HEADER = 'auto_hide_header';
export const TOGGLE_COLOR = 'color';
export const TOGGLE_WEAK = 'weak';
export const TOGGLE_MULTI_TAB = 'multi_tab';
export const APP_LANGUAGE = 'app_language';

export const CONTENT_WIDTH_TYPE = {
  Fluid: 'Fluid',
  Fixed: 'Fixed'
};

export const NAV_THEME = {
  LIGHT: 'light',
  DARK: 'dark'
};
