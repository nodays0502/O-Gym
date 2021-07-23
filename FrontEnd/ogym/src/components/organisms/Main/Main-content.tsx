import { Carousel } from "antd";

const MainContent = (): JSX.Element => {
    
     const onChange = (a: any, b?: any, c?: any) :void  => {
        console.log(a, b, c);
      }
      
      const contentStyle: any = {
        height: '160px',
        color: '#fff',
        lineHeight: '160px',
        textAlign: 'center',
        background: '#364d79',
      };
    
    return (
        <>
            <Carousel afterChange={onChange}
            
                appendDots={
                    dots => (<><ul>1</ul>
                        <ul>1</ul><ul>1</ul>
                        </>
                        )
                }
            >
                <div>
                <h3 style={contentStyle}>1</h3>
                </div>
                <div>
                <h3 style={contentStyle}>2</h3>
                </div>
                <div>
                <h3 style={contentStyle}>3</h3>
                </div>
                <div>
                <h3 style={contentStyle}>4</h3>
                </div>
            </Carousel>,
        </>
    );
}

export default MainContent;