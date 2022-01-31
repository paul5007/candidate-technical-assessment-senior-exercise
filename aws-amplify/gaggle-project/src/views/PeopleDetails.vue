<template>
  <h1>People</h1>
  <People></People>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";
import People from "@/components/People.vue";

@Options({
  components: {
    People,
  },
})
export default class PeopleDetails extends Vue {}
</script>