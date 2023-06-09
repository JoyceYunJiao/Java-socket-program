import React from 'react';
import { Grid, Card, CardContent, Typography, Button } from '@mui/material';

function ViewBoards(props) {
    return (
        <section style={{ marginTop: '32px' }}>
            <Typography variant='h2' component='h2'>Boards</Typography>
            <Grid container spacing={2}>
                {props.boards.map((board) => {
                    return (
                        <Grid item xs='12' sm='12' md='4' lg='3' key={board.id}>
                            <Card elevation='6'>
                                <CardContent>
                                    <Typography component='h4' variant='h4'>
                                        {board.name}
                                    </Typography>
                                    <Typography component='p' variant='p'>
                                        {board.desp}
                                    </Typography>
                                    <Button variant='contained' sx={{ marginTop: '16px' }}>
                                        View All Tasks
                                    </Button>
                                </CardContent>
                            </Card>
                        </Grid>
                    );
                })}
            </Grid>
        </section>
    );
};

export default ViewBoards;
