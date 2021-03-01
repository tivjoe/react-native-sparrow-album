import React from 'react';
import { View, Pressable, SafeAreaView, Text, StyleSheet } from 'react-native';
import { SparrowAlbum, SparrowAlbumView } from 'react-native-sparrow-album'

const App = () => {
    return (
        <SafeAreaView style={{ flex: 1, backgroundColor: '#c7c7c7' }} >
            <View style={{ flexDirection: 'row' }} >
                <Pressable onPress={() => SparrowAlbum.previewSelectedMedias()} >
                    <View style={[styles.button, { backgroundColor: 'blue' }]} >
                        <Text style={styles.text} >预览</Text>
                    </View>
                </Pressable>
                <Pressable onPress={async () => {
                    try {
                        const res = await SparrowAlbum.confirmSelected()
                        console.log(res)
                    } catch (e) {
                        console.log(e)
                    }
                }} >
                    <View style={[styles.button, { backgroundColor: 'red' }]} >
                        <Text style={styles.text} >确定</Text>
                    </View>
                </Pressable>
            </View>
            <SparrowAlbumView />
        </SafeAreaView>
    )
}

const styles = StyleSheet.create({
    button: {
        marginHorizontal: 15,
        marginVertical: 15,
        width: 40,
        height: 40,
        alignItems: 'center',
        justifyContent: 'center'
    },
    text: {
        fontSize: 16,
        color: 'white',
        fontWeight: 'bold'
    }
})

export default App