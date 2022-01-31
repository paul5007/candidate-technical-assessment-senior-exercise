<template>
  <div v-for="m in movies" :key="m.id">
    <p>{{ m.title }}</p>
  </div>
</template>
<script  lang="ts">
import { API } from "aws-amplify";
import { Vue } from "vue-class-component";
import { listMovies } from "../graphql/queries";

export default class Movies extends Vue {
  movies = [];

  created() {
    this.getMovies();
  }

  async getMovies() {
    const movies: any = await API.graphql({
      query: listMovies,
    });
    this.movies = movies.data.listMovies.items;
    this.movies.sort((a: any, b: any) => {
      if (a.title > b.title) {
        return 1;
      }
      if (b.title > a.title) {
        return -1;
      }
      return 0;
    });
  }
}
</script>