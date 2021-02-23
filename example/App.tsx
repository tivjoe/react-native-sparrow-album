import React from 'react';
import { View, Pressable, SafeAreaView } from 'react-native';
import { SparrowAlbum, SparrowAlbumView } from 'react-native-sparrow-album'

const App = () => {
    return (
        <SafeAreaView style={{ flex: 1, backgroundColor: '#c7c7c7' }} >
            <SparrowAlbumView />
        </SafeAreaView>
    )
}

export default App