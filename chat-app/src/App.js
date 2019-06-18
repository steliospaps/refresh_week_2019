import React from 'react';
import logo from './logo.svg';
import './App.css';

import { ApolloProvider } from 'react-apollo';
import { ApolloClient } from 'apollo-client';
import { createHttpLink } from 'apollo-link-http';
import { InMemoryCache } from 'apollo-cache-inmemory';

import Users from './Users';
import Rooms from './Rooms';

const httpLink = createHttpLink({
  uri: 'http://localhost:8080/graphql'
})

const client = new ApolloClient({
  link: httpLink,
  cache: new InMemoryCache()
});


function App() {
  return (
      <div className="App">
	<ApolloProvider client={client}>
          <Users/>
          <Rooms/>
          Hello World
        </ApolloProvider>,
      </div>
  );
}

export default App;
