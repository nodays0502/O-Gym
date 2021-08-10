import React from 'react';
import ReactDOM from 'react-dom';
import './ProfileRight.css'
import 'antd/dist/antd.css';
import { List, message, Avatar, Spin } from 'antd';
import reqwest from 'reqwest';

import InfiniteScroll from 'react-infinite-scroller';

const fakeDataUrl = 'https://randomuser.me/api/?results=5&inc=name,gender,email,nat&noinfo';

class ProfileRight extends React.Component {
  state = {
    data: [],
    loading: false,
    hasMore: true,
  };

  componentDidMount() {
    this.fetchData((res:any) => {
      this.setState({
        data: res.results,
      });
    });
  }

  fetchData = (callback:any) => {
    reqwest({
      url: fakeDataUrl,
      type: 'json',
      method: 'get',
      contentType: 'application/json',
      success: (res:any) => {
        callback(res);
      },
    });
  };

  handleInfiniteOnLoad = () => {
    let { data } = this.state;
    this.setState({
      loading: true,
    });
    if (data.length > 14) {
      message.warning('Infinite List loaded all');
      this.setState({
        hasMore: false,
        loading: false,
      });
      return;
    }
    this.fetchData((res:any) => {
      data = data.concat(res.results);
      this.setState({
        data,
        loading: false,
      });
    });
  };

  render() {
    return (
      <div className="demo-infinite-container">
        <InfiniteScroll
          initialLoad={false}
          pageStart={0}
          loadMore={this.handleInfiniteOnLoad}
          hasMore={!this.state.loading && this.state.hasMore}
          useWindow={false}
        >
          <List
            dataSource={this.state.data}
            renderItem={(item:any) => (
              <List.Item key={item.id} style={{height: '100px'}}>
                <List.Item.Meta
                  avatar={
                    <Avatar src="https://imgtag.co.kr/images/210729/210729_125520/3EYZwp.jpg" />
                  }
                  title={<a href="https://ant.design">{item.name.last}</a>}
                  description={item.email}
                />
                <div>건강 분석 페이지 이동</div>
              </List.Item>
            )}
          >
            {this.state.loading && this.state.hasMore && (
              <div className="demo-loading-container">
                <Spin />
              </div>
            )}
          </List>
        </InfiniteScroll>
      </div>
    );
  }
}

export default ProfileRight