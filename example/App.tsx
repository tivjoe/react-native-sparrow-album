import React from 'react';
import { View, Pressable } from 'react-native';
import { SparrowAlbum, SparrowAlbumView } from 'react-native-sparrow-album'

const App = () => {
    return (
        <Pressable onPress={() => SparrowAlbum.show("Awesome", SparrowAlbum.SHORT)} >
            <View style={{ width: 200, height: 200, backgroundColor: 'blue' }} />
        </Pressable>
    )
}

export default App