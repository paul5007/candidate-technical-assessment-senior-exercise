<template>
  <h1>Directors</h1>
  <div v-for="mp in moviePeople" :key="mp.id">
    <p v-if="mp.person.isDirector && !mp.person._deleted">
      {{ mp.person.name }} : {{ mp.movie.title }}
    </p>
  </div>
  <h1>Actors</h1>
  <div v-for="mp in moviePeople" :key="mp.id">
    <p v-if="!mp.person.isDirector && !mp.person._deleted">
      {{ mp.person.name }} : {{ mp.movie.title }}
    </p>
  </div>
</template>
<script  lang="ts">
import { API } from "aws-amplify";
import { Vue } from "vue-class-component";
import { listMoviePeople } from "../graphql/queries";

export default class MoviePeople extends Vue {
  moviePeople = [];
  moviePeopleMap = new Map();

  created() {
    this.getMoviePeople();
  }

  async getMoviePeople() {
    const moviePeople: any = await API.graphql({
      query: listMoviePeople,
    });
    const mp = moviePeople.data.listMoviePeople.items;
    const tempMap = new Map();
    for (let index = 0; index < mp.length; index++) {
      console.log(mp[index]);
      if (tempMap.has(mp[index].person.name)) {
        const tempList: any[] = tempMap.get(mp[index].person.name);
        tempList.push(mp[index].movie.title);
        tempList.sort((a, b) => {
          if (a > b) {
            return 1;
          }
          if (a < b) {
            return -1;
          }
          return 0;
        });
        console.log(tempList);
        tempMap.set(mp[index].person.name, tempList);
      } else {
        tempMap.set(mp[index].person.name, [mp[index].movie.title]);
      }
    }
    console.log(tempMap);
  }
}
</script>