import { ModelInit, MutableModel, PersistentModelConstructor } from "@aws-amplify/datastore";





type MovieMetaData = {
  readOnlyFields: 'createdAt' | 'updatedAt';
}

type PersonMetaData = {
  readOnlyFields: 'createdAt' | 'updatedAt';
}

type TodoMetaData = {
  readOnlyFields: 'createdAt' | 'updatedAt';
}

type MoviePersonMetaData = {
  readOnlyFields: 'createdAt' | 'updatedAt';
}

export declare class Movie {
  readonly id: string;
  readonly title: string;
  readonly People?: (MoviePerson | null)[];
  readonly description?: string;
  readonly createdAt?: string;
  readonly updatedAt?: string;
  constructor(init: ModelInit<Movie, MovieMetaData>);
  static copyOf(source: Movie, mutator: (draft: MutableModel<Movie, MovieMetaData>) => MutableModel<Movie, MovieMetaData> | void): Movie;
}

export declare class Person {
  readonly id: string;
  readonly name: string;
  readonly movies?: (MoviePerson | null)[];
  readonly isDirector: boolean;
  readonly createdAt?: string;
  readonly updatedAt?: string;
  constructor(init: ModelInit<Person, PersonMetaData>);
  static copyOf(source: Person, mutator: (draft: MutableModel<Person, PersonMetaData>) => MutableModel<Person, PersonMetaData> | void): Person;
}

export declare class Todo {
  readonly id: string;
  readonly name: string;
  readonly description?: string;
  readonly createdAt?: string;
  readonly updatedAt?: string;
  constructor(init: ModelInit<Todo, TodoMetaData>);
  static copyOf(source: Todo, mutator: (draft: MutableModel<Todo, TodoMetaData>) => MutableModel<Todo, TodoMetaData> | void): Todo;
}

export declare class MoviePerson {
  readonly id: string;
  readonly movie: Movie;
  readonly person: Person;
  readonly createdAt?: string;
  readonly updatedAt?: string;
  constructor(init: ModelInit<MoviePerson, MoviePersonMetaData>);
  static copyOf(source: MoviePerson, mutator: (draft: MutableModel<MoviePerson, MoviePersonMetaData>) => MutableModel<MoviePerson, MoviePersonMetaData> | void): MoviePerson;
}