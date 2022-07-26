const DataLoader = require("dataloader");
const express = require("express");
const axios = require("axios");
const { graphqlHTTP } = require("express-graphql");
const schema = require("./schema");

const BASE_URL = "http://localhost:8000";

function getJSONFromRelativeURL(relativeURL) {
  return axios.get(`${BASE_URL}${relativeURL}`).then((res) => res.data);
}

function getPeople() {
  return getJSONFromRelativeURL("/people/").then((json) => json.people);
}

function getPerson(id) {
  return getPersonByURL(`/people/${id}/`);
}

function getPersonByURL(relativeURL) {
  return getJSONFromRelativeURL(relativeURL).then((json) => json.person);
}

const app = express();

app.use(
  graphqlHTTP((req) => {
    // const cacheMap = new Map();
    // const peopleLoader = new DataLoader(
    //   (keys) => Promise.all(keys.map(getPeople)),
    //   { cacheMap }
    // );

    // const userLoader = new DataLoader(
    //   (keys) => Promise.all(keys.map(getPerson)),
    //   {
    //     cacheKeyFn: (key) => `/people/${key}/`,
    //     cacheMap,
    //   }
    // );

    // const personByURLLoader = new DataLoader(
    //   (keys) => Promise.all(keys.map(getPersonByURL)),
    //   { cacheMap }
    // );

    // userLoader.loadAll = peopleLoader.load.bind(peopleLoader, "__all__");
    // userLoader.loadByURL = personByURLLoader.load.bind(personByURLLoader);
    // userLoader.loadManyByURL =
    //   personByURLLoader.loadMany.bind(personByURLLoader);

    return {
      // context: {
      //   loaders: {
      //     user: userLoader,
      //   },
      // },
      graphiql: true,
      schema,
      customFormatErrorFn: (error) => ({
        message: error.message,
        locations: error.locations,
        stack: error.stack ? error.stack.split('\n') : [],
        path: error.path,
      })
    };
  })
);

app.listen(5000, () =>
  console.log("GraphQL Server running at http://localhost:5000")
);
