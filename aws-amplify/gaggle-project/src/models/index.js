// @ts-check
import { initSchema } from '@aws-amplify/datastore';
import { schema } from './schema';



const { Movie, Person, Todo, MoviePerson } = initSchema(schema);

export {
  Movie,
  Person,
  Todo,
  MoviePerson
};