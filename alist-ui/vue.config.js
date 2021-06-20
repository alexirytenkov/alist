module.exports = {
  transpileDependencies: [
    'vuetify'
  ],
  devServer: {
    proxy: {
      '/rest': {
        target: 'http://localhost:8000/'
      },
    }
  }
}
