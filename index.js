import React from 'react'
import { requireNativeComponent, NativeModules } from 'react-native';

export class SparrowAlbumView extends React.Component {

    constructor(props) {
        super(props)
        this._onChangeSelectedMedias = this._onChangeSelectedMedias.bind(this)
    }

    _onChangeSelectedMedias(event) {
        console.log(event.nativeEvent.message)
        SparrowAlbum.cropImage(event.nativeEvent.message[0].uri)
    }

    render() {
        return <RCTSparrowAlbum style={{ flex: 1 }} onChangeSelectedMedias={this._onChangeSelectedMedias} />
    }
}

const RCTSparrowAlbum = requireNativeComponent('RCTSparrowAlbum')

export const SparrowAlbum = NativeModules.SparrowAlbum;
