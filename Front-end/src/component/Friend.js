import React, { useEffect, useState } from "react";
import { Tabs, Tab, NavItem, Nav } from "react-bootstrap";
import { tokenData } from "../Apollo";
import { GetFriendFollowList, GetFriendList } from "../utils/ApiConfig";
import axios from "axios";
import { SERVER_URL } from "../utils/URL";
import { Link } from "react-router-dom";
import "../Stylefolder/Friend.css";
class Friend extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      boards: [],
      friends: [],
    };
  }
  Loadplz = async () => {
    let token = localStorage.getItem("TOKEN");
    axios.defaults.headers.common["Authorization"] = token;

    axios
      .get(SERVER_URL + "/api/v1/friends/apply")
      .then(({ data }) => {
        console.log(data.data);
        this.setState({
          boards: data.data,
        });
      })
      .catch((e) => {
        console.error(e);
      });
  };
  Loadfriend = async () => {
    let token = localStorage.getItem("TOKEN");
    axios.defaults.headers.common["Authorization"] = token;

    axios
      .get(SERVER_URL + "/api/v1/friends")
      .then(({ data }) => {
        console.log(data.data);
        this.setState({
          friends: data.data,
        });
      })
      .catch((e) => {
        console.error(e);
      });
  };
  Acceptfriend = async ({ id }) => {
    let token = localStorage.getItem("TOKEN");
    axios.defaults.headers.common["Authorization"] = token;

    axios
      .post(SERVER_URL + `/api/v1/friends/${id}/apply`)
      .then(({ data }) => {
        alert("수락완료");
        console.log(data.data);
      })
      .catch((e) => {
        console.error(e);
      });
  };
  Refusefriend = async ({ id }) => {
    let token = localStorage.getItem("TOKEN");
    axios.defaults.headers.common["Authorization"] = token;

    axios
      .delete(SERVER_URL + `/api/v1/friends/${id}`)
      .then(({ data }) => {
        console.log(data.data);
        alert("삭제 완료");
      })
      .catch((e) => {
        console.error(e);
      });
  };
  componentDidMount() {
    this.Loadfriend();
    this.Loadplz();
  }
  render() {
    console.log(this.state);
    return (
      <div>
        <thead></thead>
        <tbody>
          <tr className="friendlist">
            {this.state.friends.map((friend) => (
              <tr key={friend.friendMemberId} id="content_content">
                <td>{friend.friendMemberName}</td>
                <Link to={`/post/freeboard/letter/${friend.memberId}`}>
                  <button on>쪽지보내기</button>
                </Link>
                <button
                  onClick={() => {
                    this.Refusefriend({ id: friend.friendMemberId });
                  }}
                >
                  친구삭제
                </button>
              </tr>
            ))}
          </tr>
          <div className="bank">
            <h4>친구 요청</h4>
          </div>
          <tr className="friendplz">
            {this.state.boards.map((board) => (
              <tr key={board.friendMemberId} id="content_content">
                <span>{board.friendMemberName}</span>
                <button
                  onClick={() => {
                    this.Acceptfriend({ id: board.friendMemberId });
                  }}
                >
                  수락
                </button>
                <button
                  onClick={() => {
                    this.Refusefriend({ id: board.friendMemberId });
                  }}
                >
                  거절
                </button>
              </tr>
            ))}
          </tr>
        </tbody>
      </div>
    );
  }
}

export default Friend;
/*function Friend(props) {
  const [friendList, setFriendList] = useState(null);
  const [followlist, setfollowlist] = useState(new Map());

  useEffect(() => {
    if (!friendList || !followlist) {
      GetFriendList({ setFriendList: setFriendList });
      GetFriendFollowList({ setfollowlist: setfollowlist });
    }
  }, [friendList, followlist]);

  console.log(`Get :${followlist.get("1")} `);
  return (
    <Tabs defaultActiveKey="list" transition={false} id="noanim-tab-example">
      <Tab eventKey="list" title="친구 목록">
        {friendList ? <div> ok </div> : <div>no friend</div>}
      </Tab>
      <Tab eventKey="follower" title="친구 요청 목록">
        {followlist ? <div>{followlist}</div> : <div>No </div>}
      </Tab>
    </Tabs>
  );
}*/
