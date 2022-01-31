import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import MoviePeopleDetails from '../views/MoviePeopleDetails.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Gaggle',
    component: MoviePeopleDetails
  },
  {
    path: '/people',
    name: 'People',
    component: () => import(/* webpackChunkName: "vue-details" */ '../views/PeopleDetails.vue')
  },
  {
    path: '/movies',
    name: 'Movies',
    component: () => import(/* webpackChunkName: "vue-details" */ '../views/MoviesDetails.vue')
  },
  {
    path: '/vue-details',
    name: 'Vue Details',
    component: () => import(/* webpackChunkName: "vue-details" */ '../views/VueDetails.vue')
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
