<template>
  <h1>Movies</h1>
  <Movies></Movies>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";
import Movies from "@/components/Movies.vue";

@Options({
  components: {
    Movies,
  },
})
export default class MovieDetails extends Vue {}
</script>