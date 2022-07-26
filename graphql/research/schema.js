const { default: axios } = require("axios");
const { maskErrors } = require("graphql-errors");
const {
  GraphQLSchema,
  GraphQLList,
  GraphQLObjectType,
  GraphQLID,
  GraphQLString,
} = require("graphql");

const BASE_URL = "http://localhost:8000";

function fetchResponseByURL(relativeURL) {
  return axios
    .get(`${BASE_URL}/${relativeURL}`)
    .then((res) => res.data)
    .catch((res) => {
      throw new Error({
        code: 500,
        message: res.response.data.message
      });
    });
}

const UserType = new GraphQLObjectType({
  name: "User",
  description: "A user",
  fields: () => ({
    id: {
      type: GraphQLID,
    },
    name: {
      type: GraphQLString,
    },
    email: {
      type: GraphQLString,
    },
  }),
});

const QueryType = new GraphQLObjectType({
  name: "Query",
  description: "The root of all... queries",
  fields: () => ({
    users: {
      type: new GraphQLList(UserType),
      resolve: (root) =>
        fetchResponseByURL("v1/users/").then((json) => json.content),
    },
    user: {
      type: UserType,
      args: {
        id: { type: GraphQLID },
      },
      resolve: (root, args) => fetchResponseByURL(`v1/users/${args.id}`),
    },
  }),
});

fetchResponseByURL("v1/users")
  .catch((err) => {
    console.log(err.response.data);
  })
  .finally(console.log);

const schema = new GraphQLSchema({
  query: QueryType,
});

maskErrors(schema);

module.exports = schema;
