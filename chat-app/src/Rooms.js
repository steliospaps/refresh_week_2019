import React, { Component } from 'react';
import { Query } from 'react-apollo';
import gql from 'graphql-tag';
import Room from './Room';
import RoomCreator from './RoomCreator.js';

export default class Rooms extends Component {        
    render() {
        return (
            <div>
              <Query query={gql`query {rooms:getAllRooms{id,name}}`} >
                {({ loading, error, data }) => {
                    if (loading) return <div>Fetching</div>;
                    if (error) return <div>Error</div>;
                    
                    return (
                        <div>{data.rooms.map(room => <Room key={room.id} room={room} />)}</div>
                    );
                }}
              </Query>
              <RoomCreator/>
            </div>
        );
  }
}
