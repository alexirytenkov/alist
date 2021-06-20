<template>
  <v-app>
    <v-app-bar app><h1>A List</h1></v-app-bar>
    <v-main>
    <v-container>
    <v-row><v-textarea label="New Post" v-model="newPostText" /></v-row>
    <v-row><v-btn color="primary" @click="sendPost" :disabled="newPostText === ''">Post</v-btn></v-row>
    <v-row v-for="(post, idx) in posts.data" :key="idx">
    <v-card class="mt-2">
    <v-card-title>{{new Date(post.createdDate).toLocaleString()}}</v-card-title>
    <v-card-text>{{post.text}}</v-card-text>
    </v-card>
    </v-row>
    </v-container>
    </v-main>
  </v-app>
  
</template>

<script>

export default {
  name: 'App',

  components: {
  },

  methods: {
    getPosts() {
      fetch('/rest/post/all')
      .then(response => response.json())
      .then(data => this.posts.data = data)
    },

    sendPost() {
      fetch('/rest/post/add', { method: 'POST', headers: { 'Content-Type': 'application/json'}, body: JSON.stringify({ text: this.newPostText })})
      .then(() => {
        this.getPosts()
        this.newPostText = ''
      })
    }
  },

  data: () => ({
    posts: { data: [{ text: '' }] },
    newPostText: ''
  }),
  created() {
    this.posts.data = []
    this.getPosts()
  }
};
</script>
