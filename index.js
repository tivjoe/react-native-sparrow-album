import { requireNativeComponent, NativeModules } from 'react-native';

export const SparrowAlbumView = requireNativeComponent('RCTSparrowAlbum')
export const SparrowAlbum = NativeModules.SparrowAlbum;
