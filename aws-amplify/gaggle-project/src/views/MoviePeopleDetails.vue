<template>
  <MoviePeople></MoviePeople>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";
import MoviePeople from "@/components/MoviePeople.vue";

@Options({
  components: {
    MoviePeople,
  },
})
export default class MoviePeopleDetails extends Vue {}
</script>