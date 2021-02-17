import React from 'react';
import { View, Pressable, SafeAreaView } from 'react-native';
import { SparrowAlbum, SparrowAlbumView } from 'react-native-sparrow-album'

const App = () => {
    return (
        <SafeAreaView style={{ flex: 1, backgroundColor: '#c7c7c7' }} >
            <Pressable onPress={() => SparrowAlbum.show("Awesome", SparrowAlbum.SHORT)} >
                <View style={{ width: 200, height: 200, backgroundColor: 'blue' }} />
            </Pressable>
            <SparrowAlbumView style={{ flex: 1 }} />
        </SafeAreaView>
    )
}

export default App