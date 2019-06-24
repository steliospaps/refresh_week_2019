import React, { Component } from 'react';
import { Query } from 'react-apollo';
import gql from 'graphql-tag';
import Room from './Room';
import RoomCreator from './RoomCreator.js';
//this does not work as expected
// looks like apollo-client does not support @defer correctly currently.
//const QUERY_ROOMS = gql`query {rooms:getAllRooms{id,name,relevance @defer relevance}}`;
const QUERY_ROOMS = gql`query {rooms:getAllRooms{id,name}}`;

export default class Rooms extends Component {        
    render() {
        return (
            <div>
              <Query query={QUERY_ROOMS} >
                {({ loading, error, data }) => {
                    if (loading) return <div>Fetching</div>;
                    if (error) return <div>Error</div>;
                    console.log(JSON.stringify(data));
                    return (
                        <div>{data.rooms.map(room => <Room key={room.id} room={room} onEnterRoom={this.props.onEnterRoom} selected={this.props.room && this.props.room.id === room.id} />)}</div>
                    );
                }}
              </Query>
              <RoomCreator/>
            </div>
        );
    }
}
