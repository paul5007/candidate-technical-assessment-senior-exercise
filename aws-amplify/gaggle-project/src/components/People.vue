<template>
  <div v-for="p in people" :key="p.id">
    <p>{{ p.name }}</p>
  </div>
</template>
<script  lang="ts">
import { API } from "aws-amplify";
import { Vue } from "vue-class-component";
import { listPeople } from "../graphql/queries";

export default class People extends Vue {
  people = [];

  created() {
    this.getPeople();
  }

  async getPeople() {
    const people: any = await API.graphql({
      query: listPeople,
    });
    this.people = people.data.listPeople.items;
    this.people.sort((a: any, b: any) => {
      if (a.name > b.name) {
        return 1;
      }
      if (b.name > a.name) {
        return -1;
      }
      return 0;
    });
  }
}
</script>